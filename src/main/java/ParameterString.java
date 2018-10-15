class ParameterString implements OneParameter {
    private String param;

    public ParameterString(String param){
        this.param = param.trim ();
    }

    @Override
    public String getParam () {
        return param.substring (1, this.getFirstSpace ());
    }

    @Override
    public String getDescription () {
        return param.substring (this.getFirstSpace (), param.length ()).trim ();
    }

    private int getFirstSpace(){ //returns smallest index of first entry of space or tab in param
        return param.indexOf ("\t") < 0 ? param.indexOf (" ") : Integer.min (param.indexOf (" "), param.indexOf ("\t"));
    }
}
