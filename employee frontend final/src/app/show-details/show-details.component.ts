import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute } from '@angular/router';
import { Employment } from '../employment';



@Component({
  selector: 'app-show-details',
  templateUrl: './show-details.component.html',
  styleUrls: ['./show-details.component.css']
})
export class ShowDetailsComponent {


  
  id: number
  employee!: Employee
  newEmployment!: Employment
  constructor(private route: ActivatedRoute, private employeeService: EmployeeService) { 

    this.id=0
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.employee = new Employee();
    this.employeeService.getEmployeeById(this.id).subscribe( data => {
      this.employee = data;
    });
  }

  updateEmployee(id: number){
    // this.router.navigate(['updating-by-id', id]);
  }




  deleteEmployee(id: number){

    if(confirm("Are you sure to delete Employee ID: "+id)){
    this.employeeService.deleteEmployee(id).subscribe( data => {
      console.log(data);
      // this.getEmployees();
    })}
  }

  deleteEmploymentData(id: string){

    if(confirm("Are you sure to delete Employment ID: "+id)){
    this.employeeService.deleteEmploymentData(id).subscribe( data => {
      console.log(data);
      // this.getEmployees();
    })}
  }

  onSubmit() {
    console.log(this.employee);


    this.saveEmployee();
  }

  saveEmployee() {
    this.employeeService.addEmployee(this.employee).subscribe(data => {
      console.log(data);
      // this.goToEmployeeList();
    },
      error => console.log(error));
  }

}
