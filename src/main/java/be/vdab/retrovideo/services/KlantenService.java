package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import be.vdab.retrovideo.entities.Klant;

public interface KlantenService {
	List<Klant> findByFamilieNaamDeel(String familieNaamDeel);
	Optional<Klant> read(long id);
}
