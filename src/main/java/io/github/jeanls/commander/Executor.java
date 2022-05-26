package io.github.jeanls.commander;

import java.util.ArrayList;
import java.util.List;

public abstract class Executor {

    private List<Commander> commanders = new ArrayList<>();
    private Context context;

    public abstract void exec(final Context context);

    public Executor add(final Commander commander) {
        commanders.add(commander);
        return this;
    }

    protected Executor start(final Context context){
        this.context = context;
        return this;
    }

    public void run() {
        try {
            for (final Commander commander : commanders) {
                if (commander.canProcess(context)) {
                    commander.doProcess(context);
                }
            }
            commanders = new ArrayList<>();
        } catch (final Exception e) {
            commanders = new ArrayList<>();
            throw e;
        }
    }
}
