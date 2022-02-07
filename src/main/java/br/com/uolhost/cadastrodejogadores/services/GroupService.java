package br.com.uolhost.cadastrodejogadores.services;

import br.com.uolhost.cadastrodejogadores.dto.JsonDTO;
import br.com.uolhost.cadastrodejogadores.dto.XmlDTO;
import br.com.uolhost.cadastrodejogadores.exception.NameIsNotAvailableException;
import br.com.uolhost.cadastrodejogadores.exception.ResourceHttpIsNotAvailableException;
import br.com.uolhost.cadastrodejogadores.exception.TeamIsFullException;
import br.com.uolhost.cadastrodejogadores.modelo.Grupo;
import br.com.uolhost.cadastrodejogadores.modelo.GruposEnum;
import br.com.uolhost.cadastrodejogadores.repository.GroupRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private HttpResourceService httpService;

    public Grupo getGroupBySave(GruposEnum grupoEnum) throws TeamIsFullException, ResourceHttpIsNotAvailableException, JsonProcessingException {
        final List<Grupo> groupModels = this.repository.findByTime(grupoEnum);
        final Grupo groupModel = new Grupo();
        groupModel.setTime(grupoEnum);
        if(grupoEnum.equals(GruposEnum.OS_VINGADORES)){
            final JsonDTO heroJson = this.httpService.requestVingadores();
            final List<String> codNames = heroJson.getVingadores().stream()
                    .map(r -> r.get("codinome")).collect(Collectors.toList());
            groupModel.setCodiNome(this.getHeroAvailableBySave(groupModels, codNames));
        }else{
            final XmlDTO heroXml = this.httpService.requestLiga();
            groupModel.setCodiNome(this.getHeroAvailableBySave(groupModels, heroXml.getCodNames()));
        }
        return groupModel;
    }

    private String getHeroAvailableBySave(List<Grupo> groupModels, List<String> codNames) throws TeamIsFullException {
        final List<String> availableNames = new ArrayList<>(codNames);
        if(groupModels.size() > 1){
            groupModels.stream().forEach(to -> availableNames.removeIf(r -> r.equals(to.getCodiNome())));
        }
        if(!availableNames.isEmpty()){
            return availableNames.get(new Random().nextInt(availableNames.size()));
        }
        throw new TeamIsFullException();
    }

    public Grupo getGroupByAlter(Grupo group) throws ResourceHttpIsNotAvailableException, JsonProcessingException, NameIsNotAvailableException {
        final boolean isAvailable = !this.repository.existsByCodiNome(group.getCodiNome());
        if(isAvailable){
            final Grupo groupModel = new Grupo();
            groupModel.setTime(group.getTime());
            if(group.getTime().equals(GruposEnum.OS_VINGADORES)){
                final JsonDTO heroJson = this.httpService.requestVingadores();
                final List<String> codNames = heroJson.getVingadores().stream()
                        .map(r -> r.get("codinome"))
                        .collect(Collectors.toList());
                groupModel.setCodiNome(this.getHeroAlter(codNames, group.getCodiNome()));
            }else{
                final XmlDTO heroXml = this.httpService.requestLiga();
                groupModel.setCodiNome(this.getHeroAlter(heroXml.getCodNames(), group.getCodiNome()));

            }
            return groupModel.getCodiNome() != null ? groupModel : null;
        }
        throw new NameIsNotAvailableException();
    }

    private String getHeroAlter(List<String> codNames, String heroVerify) {
        final List<String> heroes = codNames.stream()
                .filter(r -> r.equals(heroVerify)).collect(Collectors.toList());
        if (heroes.size() == 1) {
            return heroes.get(0);
        }
        return null;
    }
}
