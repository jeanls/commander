package io.github.jeanls.commander;

public class RetryOptions {

    private final int attempts;
    private final long delay;

    public RetryOptions(int attempts, long delay) {
        this.attempts = attempts;
        this.delay = delay;
    }

    public int getAttempts() {
        return attempts;
    }

    public long getDelay() {
        return delay;
    }
}
