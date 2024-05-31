package com.invext.distribuidor.service;

import com.invext.distribuidor.model.Agent;
import com.invext.distribuidor.model.SupportRequest;
import com.invext.distribuidor.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
public class SupportRequestService {

    @Autowired
    private AgentRepository agentRepository;

    private final Queue<SupportRequest> requestQueue = new LinkedList<>();

    public String assignSupportRequest(SupportRequest request) {
        Optional<Agent> availableAgent = agentRepository.findAvailableAgentByTeam(request.getType());
        if (availableAgent.isPresent()) {
            Agent agent = availableAgent.get();
            agent.setCurrentLoad(agent.getCurrentLoad() + 1);
            agentRepository.updateAgent(agent);
            return "{\"status\": \"Ok\", \"message\": \"Solicitação atribuída ao agente: " + agent.getName() + "\"}";
        } else {
            requestQueue.add(request);
            return "{\"status\": \"Queue\", \"message\": \"Todos os agentes estão ocupados. Sua solicitação está na fila.\"}";
        }
    }

    public List<Agent> getAvailableAgents() {
        return agentRepository.findAllAvailableAgents();
    }

    public List<Agent> getAgentsQueue() {
        return agentRepository.findAll();
    }

    public Queue<SupportRequest> getRequestQueue() {
        return new LinkedList<>(requestQueue);
    }

    public void processQueuedRequests() {
        while (!requestQueue.isEmpty()) {
            SupportRequest request = requestQueue.peek();
            Optional<Agent> availableAgent = agentRepository.findAvailableAgentByTeam(request.getType());
            if (availableAgent.isPresent()) {
                Agent agent = availableAgent.get();
                agent.setCurrentLoad(agent.getCurrentLoad() + 1);
                agentRepository.updateAgent(agent);
                requestQueue.poll();
            } else {
                break;
            }
        }
    }
}
