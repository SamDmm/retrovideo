package be.vdab.retrovideo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.retrovideo.entities.Klant;
import be.vdab.retrovideo.repositories.KlantenRepository;

@Service
class DefaultKlantenService implements KlantenService {
	KlantenRepository klantenRepository;

	public DefaultKlantenService(KlantenRepository klantenRepository) {
		this.klantenRepository = klantenRepository;
	}
	
	@Override
	public List<Klant> findByFamilieNaamDeel(String familieNaamDeel) {
		return klantenRepository.findByFamilieNaamDeel(familieNaamDeel);
	}

	@Override
	public Optional<Klant> read(long id) {
		return klantenRepository.read(id);
	}
	
}
