package repository;

public interface ICrudRepository<ID, E> {

    void add(E entity);

    void remove(ID id, ID id2);

    void update(ID id, ID id2, E newEntity);

    E findById(ID id, ID id2);

}

