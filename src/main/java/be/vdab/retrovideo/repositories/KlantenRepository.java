package be.vdab.retrovideo.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Klant;

public interface KlantenRepository {
	List<Klant> findByFamilieNaamDeel(String familieNaamDeel);
	Optional<Klant> read(long id);
}
