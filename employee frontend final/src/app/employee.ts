import { DatePipe } from '@angular/common';
import { Employment } from './employment';

export class Employee {
    id!: number;
    fname!: string;
    phone!: string;
    category!: string;
    qualification!:string;
    email!: string;
    address!:string;
    salary!: number;
    office: string;
    post:string;
    dob!: string;
    joiningDate!: string;
    employmentHistory!: Employment[];
    remarks!: string;
  
    
   
  constructor() {
    // this.id = 0; 
    // // this.fname="";
    // this.lname="";
    this.email="default@gmail.com";
    this.salary=0;
    this.office="";
    this.post="";
    this.employmentHistory = [];
    // this.joiningDate = new Date();
  //  // Set the default date value
  //  const today = new Date();
  //  const year = today.getFullYear();
  //  const month = ('0' + (today.getMonth() + 1)).slice(-2); // Adding 1 because months are zero-based
  //  const day = ('0' + today.getDate()).slice(-2);

  //  this.joiningDate = `${year}-${month}-${day}`;
}

}

