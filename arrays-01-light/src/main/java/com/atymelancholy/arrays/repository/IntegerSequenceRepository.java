package com.atymelancholy.arrays.repository;

import com.atymelancholy.arrays.entity.IntegerSequence;
import com.atymelancholy.arrays.repository.observer.RepositoryObserver;
import com.atymelancholy.arrays.repository.specification.Specification;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("java:S6548")
public final class IntegerSequenceRepository {

    private static IntegerSequenceRepository instance;

    private final List<IntegerSequence> storage = new ArrayList<>();
    private final List<RepositoryObserver<IntegerSequence>> observers = new ArrayList<>();

    private IntegerSequenceRepository() {
    }

    public static IntegerSequenceRepository getInstance() {
        if (instance == null) {
            instance = new IntegerSequenceRepository();
        }
        return instance;
    }

    public void addObserver(RepositoryObserver<IntegerSequence> observer) {
        observers.add(observer);
    }

    public void add(IntegerSequence sequence) {
        storage.add(sequence);
        notifyAdd(sequence);
    }

    public boolean removeById(long id) {
        Optional<IntegerSequence> found = findById(id);
        if (found.isEmpty()) {
            return false;
        }
        IntegerSequence sequence = found.get();
        storage.remove(sequence);
        notifyRemove(sequence);
        return true;
    }

    public Optional<IntegerSequence> findById(long id) {
        for (IntegerSequence sequence : storage) {
            if (sequence.getId() == id) {
                return Optional.of(sequence);
            }
        }
        return Optional.empty();
    }

    public Optional<IntegerSequence> findByName(String name) {
        for (IntegerSequence sequence : storage) {
            if (sequence.getName().equals(name)) {
                return Optional.of(sequence);
            }
        }
        return Optional.empty();
    }

    public List<IntegerSequence> query(Specification<IntegerSequence> specification) {
        List<IntegerSequence> result = new ArrayList<>();
        for (IntegerSequence sequence : storage) {
            if (specification.isSatisfiedBy(sequence)) {
                result.add(sequence);
            }
        }
        return result;
    }

    public List<IntegerSequence> sort(Comparator<IntegerSequence> comparator) {
        List<IntegerSequence> copy = new ArrayList<>(storage);
        copy.sort(comparator);
        return copy;
    }

    public boolean updateElement(long id, int index, int value) {
        Optional<IntegerSequence> found = findById(id);
        if (found.isEmpty()) {
            return false;
        }
        IntegerSequence sequence = found.get();
        sequence.setElement(index, value);
        notifyUpdate(sequence);
        return true;
    }

    public List<IntegerSequence> getAll() {
        return new ArrayList<>(storage);
    }

    private void notifyAdd(IntegerSequence sequence) {
        for (RepositoryObserver<IntegerSequence> observer : observers) {
            observer.onAdd(sequence);
        }
    }

    private void notifyUpdate(IntegerSequence sequence) {
        for (RepositoryObserver<IntegerSequence> observer : observers) {
            observer.onUpdate(sequence);
        }
    }

    private void notifyRemove(IntegerSequence sequence) {
        for (RepositoryObserver<IntegerSequence> observer : observers) {
            observer.onRemove(sequence);
        }
    }
}
