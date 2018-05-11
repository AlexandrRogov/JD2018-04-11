package by.it.kashayed.jd01_09;



public class Var implements Operation{

//    public void abstrPubMeth(){
//        System.out.println("pubMeth");
//    }
    static Var createVar(String operand){
        operand = operand.trim().replace("\\s", "");
        if (operand.matches(Patterns.SCALAR)) return new Scalar(operand);
        if (operand.matches(Patterns.VECTOR)) return new Vector(operand);
        else
        return null;
    }
    @Override
    public String toString() {
        return "Это абстрактная переменная";
    }

    @Override
    public Var add(Var other) {
        System.out.println("Операция сложения "+this+"+"+other+" невозможна");
        return null;
    }

    @Override
    public Var sub(Var other) {
        System.out.println("Операция вычитания "+this+"-"+other+" невозможна");
        return null;
    }

    @Override
    public Var mul(Var other) {
        System.out.println("Операция умножения "+this+"*"+other+" невозможна");
        return null;
    }

    @Override
    public Var div(Var other) {
        System.out.println("Операция деления "+this+"/"+other+" невозможна");
        return null;
    }
}