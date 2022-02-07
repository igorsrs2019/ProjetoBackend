package br.com.uolhost.cadastrodejogadores.services;

import br.com.uolhost.cadastrodejogadores.dto.JogadorDTO;
import br.com.uolhost.cadastrodejogadores.dto.JsonDTO;
import br.com.uolhost.cadastrodejogadores.dto.XmlDTO;
import br.com.uolhost.cadastrodejogadores.exception.*;
import br.com.uolhost.cadastrodejogadores.modelo.Grupo;
import br.com.uolhost.cadastrodejogadores.modelo.GruposEnum;
import br.com.uolhost.cadastrodejogadores.modelo.Jogador;
import br.com.uolhost.cadastrodejogadores.repository.GroupRepository;
import br.com.uolhost.cadastrodejogadores.repository.JogadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository repository;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private HttpResourceService httpService;

    public ResponseEntity getCodiNomesAvailable() throws ResourceHttpIsNotAvailableException, JsonProcessingException {
        final JsonDTO heroJson = this.httpService.requestVingadores();
        final List<String> codNames = heroJson.getVingadores().stream().parallel()
                .map(r -> r.get("codinome"))
                .filter(r -> !this.groupRepository.existsByCodiNome(r))
                .collect(Collectors.toList());
        final List<String> heroXml = this.httpService.requestLiga().getCodNames()
                .stream()
                .parallel()
                .filter(r -> !this.groupRepository.existsByCodiNome(r))
                .collect(Collectors.toList());
        final Map<String, List<String>> response = new HashMap<>();
        response.put(GruposEnum.OS_VINGADORES.name(), codNames);
        response.put(GruposEnum.LIGA_DA_JUSTICA.name(), heroXml);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<GruposEnum, List<Jogador>>> getPlayers(){
        final Map<GruposEnum, List<Jogador>> response = this.repository.findAll()
                .stream()
                .collect(Collectors.groupingBy(p -> p.getGrupo().getTime()));
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Jogador> getPlayer(Long id) throws PlayerIsNotFoundException {
        final Optional<Jogador> player = this.repository.findById(id);
        if(player.isPresent()){
            return ResponseEntity.ok(player.get());
        }
        throw new PlayerIsNotFoundException();
    }

    public ResponseEntity save(JogadorDTO jogadorDTO) throws TeamIsFullException, NameIsNotAvailableException, ResourceHttpIsNotAvailableException, JsonProcessingException {
        if(jogadorDTO != null){
            final Jogador playerModel = new Jogador();
            playerModel.setNome(jogadorDTO.getNome());
            playerModel.setEmail(jogadorDTO.getEmail());
            playerModel.setTelefone(jogadorDTO.getTelefone());
            if(jogadorDTO.getGrupo() != null){
                playerModel.setGrupo(this.groupService.getGroupBySave(jogadorDTO.getGrupo()));
                try{
                    final Jogador player = this.repository.save(playerModel);
                    return ResponseEntity.ok(this.getUri(player));
                }catch (DataIntegrityViolationException e){
                    e.printStackTrace();
                    throw new NameIsNotAvailableException();
                }
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Jogador> alterPlayer(Jogador player) throws GroupIsRequiredException, HeroInconsistentWithTeamException, ResourceHttpIsNotAvailableException, JsonProcessingException, NameIsNotAvailableException, PlayerIsNotFoundException {
        final Optional<Jogador> playerInDataBase = this.repository.findById(player.getId());
        if(playerInDataBase.isPresent()){
            final Jogador playerFound = playerInDataBase.get();
            playerFound.setNome(player.getNome());
            playerFound.setEmail(player.getEmail());
            playerFound.setTelefone(player.getTelefone());
            if(playerFound.getGrupo().equals(player.getGrupo())) {
                return ResponseEntity.ok(this.repository.save(playerFound));
            }
            if(player.getGrupo() != null &&
                    player.getGrupo().getTime() != null &&
                    player.getGrupo().getCodiNome() != null)
            {
                final Grupo group = this.groupService.getGroupByAlter(player.getGrupo());
                if(group != null){
                    this.groupRepository.delete(playerFound.getGrupo());
                    playerFound.setGrupo(group);
                    return ResponseEntity.ok(this.repository.save(playerFound));
                }
                throw new HeroInconsistentWithTeamException();
            }
            throw new GroupIsRequiredException();
        }else{
            throw new PlayerIsNotFoundException();
        }
    }

    public void deletePlayer(Long id) throws PlayerIsNotFoundException {
        try {
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            throw new PlayerIsNotFoundException();
        }
    }

    private URI getUri(Jogador jogador) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(jogador.getId()).toUri();
    }
}
