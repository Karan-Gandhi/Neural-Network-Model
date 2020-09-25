/**
 * @author Karan Gandhi
 * @email karangandhi.programming@gmail.com
 * @desc Neural Network class
 * @class NeuralNetwork
 * @module Machine_Learning
 * @copyright Karan Gandhi
 * @example let nn = new NeuralNetwork(2, 2, 2, 0.1);
 */
class NeuralNetwork {
    /**
     * Creates an instance of NeuralNetwork.
     * @param {Number} inputs
     * @param {Number} hidden
     * @param {Number} outputs
     * @param {DoubleRange} learning_rate
     * @memberof NeuralNetwork
     */
    constructor(inputs, hidden, outputs, learning_rate) {
        if (inputs instanceof NeuralNetwork || inputs instanceof Object) {
            // hidden, input and output nodes
            this.inputs_nodes = inputs.inputs_nodes;
            this.hidden_nodes = inputs.hidden_nodes;
            this.outputs_nodes = inputs.outputs_nodes;
            // Weights for data between inputs and hidden layers
            this.weights_ih = new Matrix(inputs.weights_ih);
            // Weights for data between hidden and output layers
            this.weights_ho = new Matrix(inputs.weights_ho);
            // Bias for the hidden layer
            this.bias_h = new Matrix(inputs.bias_h);
            // Bias for the output layer
            this.bias_o = new Matrix(inputs.bias_o);
            // setting the learning rate
            this.learning_rate = inputs.learning_rate;
            this.data = inputs.data;
        } else {
            // hidden, input and output nodes
            this.inputs_nodes = inputs;
            this.hidden_nodes = hidden;
            this.outputs_nodes = outputs;
            // Weights for data between inputs and hidden layers
            this.weights_ih = new Matrix(this.hidden_nodes, this.inputs_nodes);
            // Weights for data between hidden and output layers
            this.weights_ho = new Matrix(this.outputs_nodes, this.hidden_nodes);
            // Giving random Weights
            this.weights_ih.randomSize();
            this.weights_ho.randomSize();
            // Bias for the hidden layer
            this.bias_h = new Matrix(this.hidden_nodes, 1);
            // Bias for the output layer
            this.bias_o = new Matrix(this.outputs_nodes, 1);
            // Randomise the bias of the hidden and the output layer
            this.bias_h.randomSize();
            this.bias_o.randomSize();
            // setting the learning rate
            this.learning_rate = learning_rate || 0.2;
            // labled data
            this.data = [];
        }
    }

    /**
     * adds data for traning the data
     *
     * @param {Array} array
     * @memberof NeuralNetwork
     */
    addData(...arrays) {
        for (let i = 0; i < arguments.length; i++) this.data.push(arguments[i]);
    }

    /**
     * Pridict data in the Neural Network
     *
     * @param {Array} input_arr
     * @returns
     * @memberof NeuralNetwork
     */
    predict(input_arr) {
        let input = Matrix.fromArray(input_arr);
        // Generating hidden outputs
        let hidden = Matrix.multiply(this.weights_ih, input);
        hidden.add(this.bias_h);

        // Activation function
        hidden.map(sigmoid);
        // Generating outputs for the output layer
        let output = Matrix.multiply(this.weights_ho, hidden);
        output.add(this.bias_o);
        // Activation function
        output.map(sigmoid);
        // Returning an output which is converted to an array
        return output.toArray();
    }

    /**
     * Train the Nerual network to give the desirable output
     *
     * @param {Object} options
     * @memberof NeuralNetwork
     */
    trainData(options) {
        let traningData = [],
            epochs = options.epochs || 1;
        // preparing traning data
        for (let i = 0; i < epochs; i++) for (let j = 0; j < this.data.length; j++) traningData.push(this.data[j]);
        // Shuffling the data
        shuffleArray(traningData);
        for (let input_arr of traningData) {
            let input = Matrix.fromArray(input_arr);
            // Generating hidden outputs
            let hidden = Matrix.multiply(this.weights_ih, input);
            hidden.add(this.bias_h);
            // Activation function
            hidden.map(sigmoid);
            // Generating outputs for the output layer
            let output = Matrix.multiply(this.weights_ho, hidden);
            output.add(this.bias_o);
            // Activation function
            output.map(sigmoid);
            // Converting array to Matrix object
            let targets = Matrix.fromArray(input_arr.lables);
            // Calculating the error
            // ERR = TARGETS - OUTPUTS
            let output_err = Matrix.subtract(targets, output);
            // CHANGE IN SLOPE FOR Y WHERE Y = MX + B
            // ▲ M = LEARNING_RATE * ERROR * X
            // ▲ B = LEARNING_RATE * ERROR
            // Calculating the gradient
            let gradiant = Matrix.map(output, dsigmoid);
            gradiant.multiply(output_err);
            gradiant.multiply(this.learning_rate);
            // Calculate deltas
            let hidden_t = Matrix.transpose(hidden);
            let weights_ho_delta = Matrix.multiply(gradiant, hidden_t);
            // Adjust the weights by the deltas
            this.weights_ho.add(weights_ho_delta);
            // Adjust the bias by its weights (which is the gradient)
            this.bias_o.add(gradiant);
            // Calculate the hidden layer outputs
            let weight_ho_transposed = Matrix.transpose(this.weights_ho);
            let hidden_errs = Matrix.multiply(weight_ho_transposed, output_err);
            // Calculate hidden gradient
            let hidden_gradient = Matrix.map(hidden, dsigmoid);
            hidden_gradient.multiply(hidden_errs);
            hidden_gradient.multiply(this.learning_rate);
            // Calculate input -> hidden deltas
            let input_t = Matrix.transpose(input);
            let weights_ih_delta = Matrix.multiply(hidden_gradient, input_t);
            // Adjust the weights by the deltas
            this.weights_ih.add(weights_ih_delta);
            // Adjust the bias by its weights (which is the gradient)
            this.bias_h.add(hidden_gradient);
        }
    }

    /**
     * Normalise the data into values between 0 and 1
     *
     * @param {Number} normaliser
     * @memberof NeuralNetwork
     */
    normaliseData(normaliser) {
        let max = normaliser || findMax(this.data);
        for (let i = 0; i < this.data.length; i++) for (let j = 0; j < this.data[i].length; j++) this.data[i][j] = this.data[i][j] / max;
    }

    /**
     * Returns a copy of a Neural Network
     *
     * @static
     * @param {NeuralNetwork} nn
     * @returns
     * @memberof NeuralNetwork
     */
    static copy(nn) {
        return new NeuralNetwork(nn);
    }

    /**
     * Saves the given Neural Network to a encoded file
     *
     * @memberof NeuralNetwork
     */
    saveModel() {
        const file = new Blob([window.btoa(JSON.stringify(this))], { type: "text/plain;charset=utf-8" });
        if (window.navigator.msSaveOrOpenBlob) window.navigator.msSaveOrOpenBlob(file, "weights.txt");
        else {
            let a = document.createElement("a"),
                url = URL.createObjectURL(file);
            a.href = url;
            a.download = "weights.txt";
            document.body.appendChild(a);
            a.click();
            setTimeout(() => {
                document.body.removeChild(a);
                window.URL.revokeObjectURL(url);
            }, 0);
        }
    }

    /**
     * Load a neural Network created and saved before
     *
     * @static
     * @param {FileName} fn
     * @returns
     * @memberof NeuralNetwork
     */
    static async loadModel(fn) {
        const url = (await window.location.href) + `${fn}   `;
        const respose = await fetch(url);
        const txt = await respose.text();
        const decode = window.atob(txt);
        const json = JSON.parse(decode);
        return new NeuralNetwork(json);
    }
}

/**
 * Takes any value and converts it to a number between 0 and 1
 *
 * @param {DoubleRange} x
 * @returns
 */
function sigmoid(x) {
    return 1 / (1 + Math.exp(-x));
}

/**
 * Drivid of sigmoid
 *
 * @param {DoubleRange} y
 * @returns
 */
function dsigmoid(y) {
    return y * (1 - y);
}

/**
 * Shuffle the data in a array
 *
 * @param {Array} array
 */
function shuffleArray(array) {
    array.sort(() => Math.random() - 0.5);
}

/**
 *
 *
 * @param {Array} array
 * @returns
 */
function findMax(array) {
    let max = -Infinity;
    for (let i = 0; i < array.length; i++) {
        for (let j = 0; j < array[i].length; j++) {
            if (max < array[i][j]) max = array[i][j];
        }
    }
    return max;
}
