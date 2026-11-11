package sidhardhjoe.java.learnjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sidhardhjoe.java.learnjava.bean.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/students") // Request mapping used at a class level will set as a base URL and prevent repetition in method Mapping
public class StudentController {

    /*
    API for getting a student

    @GetMapping annotation will specify the URL (endpoint) and it returns a Student Object that is created from the
    Java Bean (Object)
    */
    @GetMapping("student")
    public Student getStudent(){
        return new Student(
           1, "Sidhardh", "Joe"
        );
    }


    /*
    API for returning a list of students

    @GetMapping annotation will specify the URL (endpoint) and it returns a Student Object that is created from the
    Java Bean (Object)

    Since a list is being returned the return type is a list of Student Object,
    a new list is created in the method which is an array list since we are returning a array of list.
    and we are returning the list
    */
    @GetMapping("students-list")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alex", "Jacob"));
        students.add(new Student(2, "Jacob", "Bastion"));
        students.add(new Student(2, "Tony", "Skank"));
        return students;
    }


    /*
    API with URI param

    @GetMapping annotation will specify the URL (endpoint) and it returns a Student Object that is created from the
    Java Bean (Object)

    @PathVariable will take in all the uri path variables and then use ("nameOfPathVariable") to bind it to the
    Parameter name. If the path variable and parameter is of the same name then this is not needed.
    */
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student getStudentDetails(@PathVariable("id") int studentId,
                                     @PathVariable("first-name") String firstName,
                                     @PathVariable("last-name") String lastName
                                     ){
        return new Student(studentId, firstName, lastName);
    }


    /*
    API with request parameter

    @GetMapping annotation will specify the URL (endpoint) and it returns a Student Object that is created from the
    Java Bean (Object)

    @RequestParam will take in all the variables that are coming in as query parameter "?",
    the url does not need to specify all the parameters. URL will be /students/query?id=3&firstName=Joe&lastName=ben.
    required false makes it not a required field.
    Main difference between @PathVariable and @RequestPara is that @PathVariable is used to query by id and @RequestParam
    is more for searching will different constraints (Just one example it can vary).
     */
    @GetMapping("query")
    public Student queryStudents(
            @RequestParam int id,
            @RequestParam(required = false) String firstName,
            @RequestParam String lastName
    ){
        return new Student(id, firstName, lastName);
    }


    /*
    API for post request

    @PostMapping annotation will handle all the incoming HTTP post requests
    @ResponseStatus will return the specified status code by default it is 200
    @RequestBody will handle the body that comes with the post request. It will convert the incoming JSON to java object

    This will simply return a new student object that came in
    */
    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public Student postStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }



    /*
    API for updating an existing record

    @PutMapping annotation will handle all the incoming HTTP PUT requests
    @RequestBody will handle the body that comes with the post request

    Since this is a put request it will have a URI path variable and RequestBody with a body to the PUT.
    @PathVariable will handle the URI variable and @RequestBody will handle the incoming request's JSON body

    This will simply return a new student object that came in
    */
    @PutMapping("{id}/update")
    public Student udpateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        student.setId(studentId);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }


    /*
    API to delete

    @DeleteMapping will handle all incoming DELETE request to this endpoint.
    since the endpoint has a path variable and is different to its method parameter we use @PathVariable with binding to it
     */
    @DeleteMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") String studentId){
        System.out.println(studentId);
        return "Student deleted successfully";
    }


    // ResponseEntity allows you to have a custom HTTP response, everything can be configured using ResponseEntity class
    @GetMapping("response-entity")
    public ResponseEntity<Student> getWithResponseEntity(){
        Student student = new Student(3, "Joe", "Bencylal");
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    // this is by using ResponseEntity with more modified response... updated header and status code with body
    /*
    new ResponseEntity is using a constructor and the other one is using a builder.
    builder is more simple as it can be chained, constructor everything needs be passed in as a parameter,
    so an HTTP header will require a new HttpHeaders() and HttpHeaders.add(name, value).
    builder is more simple.

    NOTE: builder needs to be chained in the proper way. .status().header().body()
     */
    @GetMapping("response-entity/custom-header")
    public ResponseEntity<Student> getWithResponseEntityCustomHeader(){
        Student student = new Student(3, "Joe", "Bencylal");
        return ResponseEntity.ok().header("custom-header", "Joe").body(student);
    }
}
