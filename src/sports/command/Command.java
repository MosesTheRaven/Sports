package sports.command;

import java.util.List;

import sports.base.SportClub;

public abstract class Command {
	public abstract void execute();
	public abstract List<SportClub> getClubs();
}
