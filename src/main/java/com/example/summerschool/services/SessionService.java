package com.example.summerschool.services;

import com.example.summerschool.dto.SaveSessionDto;
import com.example.summerschool.models.Organizer;
import com.example.summerschool.models.Session;
import com.example.summerschool.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private OrganizerRepository organizerRepository;

    public SessionService(SessionRepository sessionRepository , OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
        this.sessionRepository = sessionRepository;
    }

    public Integer save(SaveSessionDto saveSessionDto) {
        Organizer org = organizerRepository.findById(saveSessionDto.getOrganizerID()).orElse(null);
        Session s = new Session();
        s.setTitle(saveSessionDto.getTitle());
        s.setInsight(saveSessionDto.getInsight());
        s.setRequirements(saveSessionDto.getRequirements());
        s.setOrganizer(org);
        Session savedSession = sessionRepository.save(s);
        return savedSession.getId();
    }

    public Session findById(Integer id) {
        Session session = sessionRepository.findById(id).orElse(null);
        return session;
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public void update(Integer id, SaveSessionDto updatedSessionDto) {
        Session sessionToUpdate = sessionRepository.findById(id).orElse(null);
        if (sessionToUpdate != null) {
            Organizer org = organizerRepository.findById(updatedSessionDto.getOrganizerID()).orElse(null);
            sessionToUpdate.setTitle(updatedSessionDto.getTitle());
            sessionToUpdate.setInsight(updatedSessionDto.getInsight());
            sessionToUpdate.setRequirements(updatedSessionDto.getRequirements());
            sessionToUpdate.setOrganizer(org);
            sessionRepository.save(sessionToUpdate);
        }
    }

        public void delete(Integer id) {
            sessionRepository.deleteById(id);
        }

}
