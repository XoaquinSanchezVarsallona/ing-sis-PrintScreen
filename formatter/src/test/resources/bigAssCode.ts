let x :    number =   10;
let y : number   = 20;
let z :   string =   "Hello";
let a              :string    = "World";


x = 9;
y = 15;
z = "Goodbye";
a = "Everyone";


println(x        +y);
println(  y);
println(z);
println(a);


let sum: number = x+ y;
let diff: number = y    -x;
let prod: number = x*y;
let div: number = y/        x;


println(sum);
println(diff);
println(prod);
println(div);


let greeting: string = z + ", " + a;
println(greeting);


let mixed1: string = z +x;
let mixed2: string =     x+ z;
let mixed3        : string= x + ", " + a;









println(mixed1);
println(mixed2);
println(mixed3);


let b: number=   100;
let c  : string= "Test";
let   d: number     =   200;
let e: string    =  "More Strings";


b =        150    ;
c = "Updated Test";
d = 300;
e = "Even More Strings";

if (true) {
    println(a +b);
}

if (false) {
    let si: number = 23;
    println(si*2 + a + b);
} else {
    b =          10;
    d = b+3;
}

println(     b);
println(c     );
println(    d);
println(e);


let combo1: string = b + e;
let combo2: string = e + d;
println(combo1);
println(combo2);


let complex: string = (b + x)+      " is a combination";
println(       complex);


b = b - 50;
println(b);

c = c + " and more";
println(c);


let specialCase         : string     =   "Result: "+ (x + y);
println(specialCase);


let        edge1 :string = z+(x+y);
let  edge2      : string= (x* y) +         a;
println(edge1);
println(edge2);