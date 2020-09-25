public class NeuralNetwork
{
    int input_nodes;
    int hidden_nodes;
    int output_nodes;
    Matrix weights_ih;
    Matrix weights_ho;
    Matrix bias_h;
    Matrix bias_o;
    double learningRate;
    traningData[] td;
    
    public NeuralNetwork(final int input_nodes, final int hidden_nodes, final int output_nodes) {
        this.td = new traningData[4];
        this.input_nodes = input_nodes;
        this.hidden_nodes = hidden_nodes;
        this.output_nodes = output_nodes;
        (this.weights_ih = new Matrix(this.hidden_nodes, this.input_nodes)).randomSize();
        (this.weights_ho = new Matrix(this.output_nodes, this.hidden_nodes)).randomSize();
        (this.bias_h = new Matrix(this.hidden_nodes, 1)).randomSize();
        (this.bias_o = new Matrix(this.output_nodes, 1)).randomSize();
        this.learningRate = 0.1;
    }
    
    public double[] feedFoward(final double[] array) {
        final Matrix multiply = Matrix.multiply(this.weights_ih, Matrix.fromArray(array));
        multiply.add(this.bias_h);
        multiply.sigmoid();
        final Matrix multiply2 = Matrix.multiply(this.weights_ho, multiply);
        multiply2.add(this.bias_o);
        multiply2.sigmoid();
        return multiply2.toArray();
    }
    
    public void train(final double[] array, final double[] array2) {
        final Matrix fromArray = Matrix.fromArray(array);
        final Matrix multiply = Matrix.multiply(this.weights_ih, fromArray);
        multiply.add(this.bias_h);
        multiply.sigmoid();
        final Matrix multiply2 = Matrix.multiply(this.weights_ho, multiply);
        multiply2.add(this.bias_o);
        multiply2.sigmoid();
        final Matrix subtract = Matrix.subtract(Matrix.fromArray(array2), multiply2);
        final Matrix dsigmoid = Matrix.dsigmoid(multiply2);
        dsigmoid.multiply(subtract);
        dsigmoid.multiply(this.learningRate);
        this.weights_ho.add(Matrix.multiply(dsigmoid, Matrix.transpose(multiply)));
        this.bias_o.add(dsigmoid);
        final Matrix multiply3 = Matrix.multiply(Matrix.transpose(this.weights_ho), subtract);
        final Matrix dsigmoid2 = Matrix.dsigmoid(multiply);
        dsigmoid2.multiply(multiply3);
        dsigmoid2.multiply(this.learningRate);
        this.weights_ih.add(Matrix.multiply(dsigmoid2, Matrix.transpose(fromArray)));
        this.bias_h.add(dsigmoid2);
    }
    
    public static String toElements(final double[][] array) {
        String string = "[";
        for (int i = 0; i < array.length; ++i) {
            String s = string + "[";
            for (int j = 0; j < array[i].length; ++j) {
                s = s + array[i][j] + ", ";
            }
            string = s + "\b\b], ";
        }
        return string + "\b\b]";
    }
    
    @Override
    public String toString() {
        return "NeuralNetwork = {\n\tinput_nodes : " + this.input_nodes + ", \n\thidden_nodes : " + this.hidden_nodes + ", \n\toutput_nodes : " + this.output_nodes + ", \n\tweights_ih : " + toElements(this.weights_ih.matrix) + ", \n\tweights_ho : " + toElements(this.weights_ho.matrix) + ", \n\tbias_h : " + this.bias_h + ", \n\tbias_o : " + this.bias_o + ", \n\tlearningRate : " + this.learningRate + "\n}";
    }
    
    public static void main(final String[] array) {
        final double[] array2 = { 12.0, 3.0 };
        System.out.print(new NeuralNetwork(1, 3, 2));
    }
}