export const uuidv4 = () => {
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, c => {
		const r = (Math.random() * 16) | 0;
		const v = c == 'x' ? r : (r & 0x3) | 0x8;
		return v.toString(16);
	});
};
export const log = value => console.log(value);
export const head = ([x]) => x;
export const tail = ([x, ...tail]) => tail;
export const def = x => typeof x !== 'undefined';
const isAFun = f => typeof f === 'function';
export const undef = x => !def(x);
export const copy = array => [...array];
export const length = ([x, ...xs], len = 0) =>
	def(x) ? length(xs, len + 1) : len;
export const reverse = ([x, ...xs]) => (def(x) ? [...reverse(xs), x] : []);
export const first = ([x, ...xs], n = 1) =>
	def(x) && n ? [x, ...first(xs, n - 1)] : [];
export const last = (xs, n = 1) => reverse(first(reverse(xs), n));
export const slice = ([x, ...xs], i, y, curr = 0) =>
	def(x)
		? curr === i
			? [y, x, ...slice(xs, i, y, curr + 1)]
			: [x, ...slice(xs, i, y, curr + 1)]
		: [];
export const isArray = x => Array.isArray(x);

export const flatten = ([x, ...xs]) =>
	def(x)
		? isArray(x)
			? [...flatten(x), ...flatten(xs)]
			: [x, ...flatten(xs)]
		: [];

//const array = [1,2,3,4,5]
//swap(array, 0, 4) // [5,2,3,4,1]
export const swap = (a, i, j) =>
	map(a, (x, y) => {
		if (y === i) return a[j];
		if (y === j) return a[i];
		return x;
	});

//   const double = x => x * 2
//   map([1,2,3], double) // [2,4,6]
export const map = ([x, ...xs], fn) => {
	if (undef(x)) return [];
	return [fn(x), ...map(xs, fn)];
};

export const filter = ([x, ...xs], fn) =>
	def(x) ? (fn(x) ? [x, ...filter(xs, fn)] : [...filter(xs, fn)]) : [];

export const reject = ([x, ...xs], fn) => {
	if (undef(x)) return [];
	if (!fn(x)) {
		return [x, ...reject(xs, fn)];
	} else {
		return [...reject(xs, fn)];
	}
};
export const partition = (xs, fn) => [filter(xs, fn), reject(xs, fn)];

// const sum = (memo, x) => memo + x
// reduce([1,2,3], sum, 0) // 6

// const flatten = (memo, x) => memo.concat(x)
// reduce([4,5,6], flatten, [1,2,3]) // [1,2,3,4,5,6]

export const reduce = ([x, ...xs], fn, memo, i = 0) =>
	def(x) ? reduce(xs, fn, fn(memo, x, i), i + 1) : memo;

export const reduceRight = (xs, fn, memo) => reduce(reverse(xs), fn, memo);

// const flatten = (memo, x) => memo.concat(x)

// reduceRight([[0,1], [2,3], [4,5]], flatten, []) // [4, 5, 2, 3, 0, 1]
export const partial = (fn, ...args) => (...newArgs) => fn(...args, ...newArgs);

// const add = ([x, ...xs]) => def(x) ? parseInt(x + add(xs)) : []
// add([1,2,3,4,5]) // 15

// const spreadAdd = spreadArg(add)
// spreadAdd(1,2,3,4,5) // 15
export const spreadArg = fn => (...args) => fn(args);

// Extract property value from array. Useful when combined with the map function.
// const product = {price: 15}
// pluck('price', product) // 15

// const getPrices = partial(pluck, 'price')
// const products = [
//   {price: 10},
//   {price: 5},
//   {price: 1}
// ]
// map(products, getPrices) // [10,5,1]
export const pluck = (key, object) => object[key];

// Flow =>Each function consumes the return value of the function that came before.

export const flow = (...args) => init =>
	reduce(args, (memo, fn) => fn(memo), init);

// const getPrice = partial(pluck, 'price')
// const discount = x => x * 0.9
// const tax = x => x + (x * 0.075)
// const getFinalPrice = flow(getPrice, discount, tax)

// // looks like: tax(discount(getPrice(x)))
// // -> get price
// // -> apply discount
// // -> apply taxes to discounted price

// const products = [
//   {price: 10},
//   {price: 5},
//   {price: 1}
// ]

// map(products, getFinalPrice) // [9.675, 4.8375, 0.9675]

// The same as flow, but arguments are applied in the reverse order.
//  Compose matches up more naturally with how functions are written.
//  Using the same data as defined for the flow function:
export const compose = (...args) => flow(...reverse(args));

// const getFinalPrice = compose(tax, discount, getPrice)

// // looks like: tax(discount(getPrice(x)))

// map(products, getFinalPrice) // [9.675, 4.8375, 0.9675]

const minOrMax = (arr, predicateFun) => {
	predicateFun = isAFun(predicateFun) ? predicateFun : (a, b) => a < b;
	return arr.reduce((acc, v) => (predicateFun(acc, v) ? acc : v));
};
// intersection in js intersect (arr1, arr2) => arr contain commun element btw arr1 and arr2
export const intersect = (arr1, arr2) =>
	arr1.filter(item => arr2.includes(item));

//zip fiunction
export const zip = (arr1, arr2) => arr1.map((item, i) => [item, arr2[i]]);
// read from obj nested path
export const read = (obj, path) =>
	path.split('.').reduce((acc, val) => (!acc ? undefined : acc[val]), obj);

/*
 * Creates an array without any duplicate item.
 * If a key function is passed, items will be compared based on the result of that function;
 * if not, their string representation will be used.
 */
export function distinct(arr, keyFunction) {
	keyFunction = keyFunction || (x => x);

	let set = {};
	let result = [];

	arr.forEach(item => {
		let key = keyFunction(item);
		if (!set[key]) {
			set[key] = 1;
			result.push(item);
		}
	});

	return result;
}

/*
 * function check if two object are equals
 * ps : note that's function dont work with nested object
 * its should add recursive call im limited by time :)
 */
export function objectEquals(obj: object, obj2: object): boolean {
	if (Object.keys(obj).length === Object.keys(obj2).length) {
		for (const key of Object.keys(obj)) {
			if (obj[key] !== obj2[key]) return false;
		}
		return true;
	}
	return false;
}
//functors to avoid null and undefined
//Maybe(empty).map(log); // does not log
//Maybe('Maybe Foo').map(log); // logs "Maybe Foo"

const Just = value => ({
	map: f => Just(f(value))
});
const Nothing = () => ({
	map: () => Nothing()
});
