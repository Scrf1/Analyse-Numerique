	public double error(ArrayList<Double> UApproche,ArrayList<Double> UExact){
        double error = Math.abs( UExact.get(0)-UApproche.get(0));
        for(int i=1;i<UApproche.size();i++){
            double err=Math.abs( UExact.get(i)-UApproche.get(i));
            if(err>error) error=err;
        }
        return error;
    }