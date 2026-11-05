package service;

import domain.Tort;
import repository.IRepository;
import repository.RepositoryException;

import java.util.Collection;

public class ServiceTort {
    IRepository<Tort> tortRepo;

    public ServiceTort(IRepository tortRepo) throws RepositoryException {
        this.tortRepo = tortRepo;

    }

    public void addTort(int id, String tip) throws RepositoryException, ServiceException {
        if (id<0) {
            throw new ServiceException(("id ul nu poate fi negativ!"));
        }
        if(tip == null)
            throw new ServiceException(("tip is null"));

        Tort tort = new Tort(id, tip);
        tortRepo.add(tort);
    }

    public Collection<Tort> getAlltortuti (){
        return tortRepo.getAll();
    }

    public void deleteTort (int id) throws RepositoryException {
        tortRepo.remove(id);
    }

    public void updateTort (int id, String tipNou) throws RepositoryException {
        if(tipNou == null)
            throw new ServiceException(("tipul nu poate fi null"));
        Tort tortNou = new Tort(id, tipNou);
        tortRepo.update(id, tortNou);
    }

    public Tort findTortbyId (int id) throws RepositoryException {
        return tortRepo.findById(id);
    }
}
