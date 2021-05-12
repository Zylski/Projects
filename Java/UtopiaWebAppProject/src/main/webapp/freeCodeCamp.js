//Programmer: Damian Zylski
//Date: 3/30/2021
//Program: To learn some basics about JS

try
{
    //import { func } from "./firstprogram";

}
catch(e)
{
    console.error(e);
    SyntaxError("HELP!");
}

"use strict";

var name = "Damian Zylski";
let id = 3785564;
const pi = 3.14;

name = "Z" + name;
console.log(name);

var myArr = ["Z", 4, [3,2]];

myArr.push("I love Tacos");

myArr.shift("Quack");

function f(truth)
{
    console.log(truth);
}

var truth = myArr.pop();

var i = 0;
while(i < 10)
{
    f(truth);
    i++;
}

if(typeof(myArr) != null)
{
    console.warn("Array exists");
}

var student = 
{
    "name": "Damian Zylski",
    "id": 3785564
};

var food = 
{
    "type": 
    {
        "subtype": "pizza"  
    }
};

console.log(food.type.subtype);

student["level"] = "Senior";

delete student.level;


console.log(student.hasOwnProperty("names"));

console.log(JSON.stringify(student));

console.log(Math.floor(Math.random() * 10) + 10);

console.log(parseInt("1010",2));

const numArr = [2,3];
Object.freeze(numArr);

try
{
    numArr[0] = 666;
}
catch(ex)
{
    console.error(ex);
}

function doSomething(...args)
{
    var sum = 0;

    

    for(var i = 0; i < args.length; i++)
    {
        sum = sum + args[i];
        
    }

    var newArr = [55, 44, ...args];
    return newArr[3];
}

const {"name": y, "id": z} = student;

console.log(z);

console.log(numArr[0]);

console.log(doSomething(5,10,20));

function makePerson()
{
    class Person
    {
        constructor(firstName, lastName)
        {
            this._firstName = firstName;
            this._lastName= lastName;
        }

        //getter
        get firstName()
        {
            return this._firstName;
        }
        get lastName()
        {
            return this._lastName;
        }
        //setter
        set firstName(f)
        {
            this._firstName = f;
        }
        set lastName(l)
        {
            this._lastName = l;
        }
    }
    return Person;
}

const Person = makePerson();
var p = new Person("Bob","Smith");

console.log(p.lastName);

console.log(student["name"]);

