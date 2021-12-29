import java.util.*;
import java.util.LinkedList;

// TC - O(n) - V(vertices) or N - no. of employees
// SC - O(n) - by the hashmap, actually V(vertices or no. of employees)+E(their subordinates), recursive stack - O(n)
public class EmployeeImportance {

    class Employee {
        public int id;
        public int importance;
        public List <Integer> subordinates;
    }
    Map <Integer, Employee> map;
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        // inorder to access the employee object in O(1) time, we are storing the information in hashmap
        for(Employee emp : employees) {
            if(!map.containsKey(emp.id)) {
                map.put(emp.id, emp);
            }
            map.put(emp.id, emp);
        }

        return getImportanceDFS(id);
    }

    private int getImportanceDFS(int id) {
        Employee employee = map.get(id);
        int imp = employee.importance;
        List<Integer> reports = employee.subordinates;
        // calling the DFS on its subordinates, to return back their answers(importance)
        for(int i : reports) {
            imp += getImportanceDFS(i);
        }

        //returning the importance back
        return imp;
    }

    // TC - O(n) - V(vertices) or N - no. of employees
    // SC - O(n) - by the hashmap, actually V(vertices or no. of employees)+E(their subordinates), queue space - O(n)
    public int getImportanceBFS(List<Employee> employees, int id) {
        map = new HashMap<>();
        // inorder to access the employee object in O(1) time, we are storing the information in hashmap
        for(Employee emp : employees) {
            if(!map.containsKey(emp.id)) {
                map.put(emp.id, emp);
            }
            map.put(emp.id, emp);
        }

        int totalImp = 0;
        Queue<Employee> q = new LinkedList <>();
        q.add(map.get(id)); // adding the id asked in the request
        Employee emp;
        while(!q.isEmpty()) {
            emp = q.poll();
            totalImp += emp.importance;
            List<Integer> reports = emp.subordinates;
            for(int subordinate : reports) {
                q.add(map.get(subordinate)); // adding the subordinates, 1 by 1 in the queue
                // accessing it from hashmap to reduce time and getting in O(1)
            }
        }

        return totalImp; // returning the total importance
    }
}
