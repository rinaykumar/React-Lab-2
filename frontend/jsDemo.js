console.log('Run me!'); // print

// Variables
var test = ''; // old don't use this anymore

// These 2 are good
let testLet = 'String'; // String
testLet = 9; // Number okay to change
const testConst = '';
// testConst = 0; // not okay, const cannot be changed

// Functions
function functionName(a) {
  console.log(a);
  return a + ' This is the function output';
}

const arrowFunction = a => {
  // roughly lambda
  console.log(a);
  return a;
};

const output = functionName('Hello world');
console.log(output);
functionName(9);
arrowFunction('Hello World');

// Arrays
const myArray = [9, 7, '12343', []]; // arrays can have any data type
console.log(myArray[2]); // 12343
console.log(myArray[100]); // undefined
myArray[0] = 'asd';
myArray[110] = 'This was out of bounds'; // works, okay to add out of bounds
myArray[109] = null; // null is different than undefined
console.log(myArray[109]); // undefined
console.log(myArray) // still runs with no semicolon

