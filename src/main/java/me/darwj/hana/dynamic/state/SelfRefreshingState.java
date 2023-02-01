package me.darwj.hana.dynamic.state;

import me.darwj.hana.Hana;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

/**
 * Represents any in-game state which self-refreshes occasionally, synchronously or asynchronously.<br>
 * A state can be anything such as a block, entity, world etc.
 *
 * @param <T> The state which gets modified
 */
public class SelfRefreshingState<T> {

    private T state;
    private final Consumer<T> refreshingMethod;

    private BukkitTask refreshingTask;
    private boolean isAsync = false;
    public boolean isAsync() { return isAsync; }

    private int refreshRateTicks = 1;
    public int getRefreshRateTicks() { return refreshRateTicks; }

    public SelfRefreshingState(int refreshRateTicks, T initialState,
                               Consumer<T> refreshingMethod, boolean isAsync) {
        this.refreshRateTicks = refreshRateTicks;
        this.state = initialState;
        this.refreshingMethod = refreshingMethod;
        this.isAsync = isAsync;
        startState();
    }

    public void startState() {
        if (!isAsync) {
            refreshingTask = Bukkit.getScheduler().runTaskTimer(Hana.getInstance(),
                    () -> refreshingMethod.accept(state), 0, refreshRateTicks);
        } else {
            refreshingTask = Bukkit.getScheduler().runTaskTimerAsynchronously(Hana.getInstance(),
                    () -> refreshingMethod.accept(state), 0, refreshRateTicks);
        }
    }
    public void endState() { refreshingTask.cancel(); }

}
