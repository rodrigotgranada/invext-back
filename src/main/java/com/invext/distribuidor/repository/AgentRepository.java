package com.invext.distribuidor.repository;

import com.invext.distribuidor.model.Agent;
import com.invext.distribuidor.service.SupportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AgentRepository {

    private final List<Agent> agents = new ArrayList<>();

    @Autowired
    private ApplicationContext applicationContext;

    public Optional<Agent> findAvailableAgentByTeam(String team) {
        return agents.stream()
                .filter(agent -> agent.getTeam().equalsIgnoreCase(team) && agent.getCurrentLoad() < 3)
                .findFirst();
    }

    public List<Agent> findAllAvailableAgents() {
        return agents.stream()
                .filter(agent -> agent.getCurrentLoad() < 3)
                .toList();
    }

    public List<Agent> findAll() {
        return new ArrayList<>(agents);
    }

    public void updateAgent(Agent agent) {
        agents.set(agents.indexOf(agent), agent);
        if (agent.getCurrentLoad() < 3) {
            SupportRequestService supportRequestService = applicationContext.getBean(SupportRequestService.class);
            supportRequestService.processQueuedRequests();
        }
    }

    public void addAgent(Agent agent) {
        agents.add(agent);
    }
}
