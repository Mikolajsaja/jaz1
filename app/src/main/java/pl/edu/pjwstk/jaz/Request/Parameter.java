package pl.edu.pjwstk.jaz.Request;

public class Parameter {

    private String parameterKey;
    private String parameterValue;

    public Parameter(String parameterKey, String parameterValue) {
        this.parameterKey = parameterKey;
        this.parameterValue = parameterValue;
    }

    public Parameter() {
    }

    public String getParameterKey() {
        return parameterKey;
    }

    public String getParameterValue() {
        return parameterValue;
    }
}
