package be.vdab.retrovideo.web;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
class DefaultMandje implements Serializable, Mandje {
	private static final long serialVersionUID = 1L;
	private final Set<Long> filmIds = new HashSet<>();
	
	@Override
	public void addFilmId(long filmId) {
		filmIds.add(filmId);
	}
	@Override
	public Set<Long> getFilmIds() {
		return filmIds;
	}
	@Override
	public void deleteFilmId(long filmId) {
		filmIds.remove(filmId);
	}
	@Override
	public void maakLeeg() {
		filmIds.clear();
	}
}
