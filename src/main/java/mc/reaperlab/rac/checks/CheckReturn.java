package mc.reaperlab.rac.checks;

public class CheckReturn {

    public boolean passed;
    public String reason;

    public CheckReturn(boolean passed, String reason) {this.passed=passed;this.reason=reason;}

    public boolean isPassed() {
        return this.passed;
    }
}
