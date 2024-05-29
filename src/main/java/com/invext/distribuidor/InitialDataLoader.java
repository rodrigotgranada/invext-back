package com.invext.distribuidor;

import com.invext.distribuidor.model.Agent;
import com.invext.distribuidor.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public void run(String... args) throws Exception {
        agentRepository.addAgent(new Agent("1", "Alice", "Cartões"));
        agentRepository.addAgent(new Agent("2", "Bob", "Cartões"));
        agentRepository.addAgent(new Agent("3", "Charlie", "Cartões"));

        agentRepository.addAgent(new Agent("4", "Dave", "Empréstimos"));
        agentRepository.addAgent(new Agent("5", "Eve", "Empréstimos"));
        agentRepository.addAgent(new Agent("6", "Frank", "Empréstimos"));

        agentRepository.addAgent(new Agent("7", "Grace", "Outros Assuntos"));
        agentRepository.addAgent(new Agent("8", "Heidi", "Outros Assuntos"));
        agentRepository.addAgent(new Agent("9", "Ivan", "Outros Assuntos"));
    }
}
