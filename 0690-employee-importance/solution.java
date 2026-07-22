import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {

    private Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {

        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }

        return dfs(id);
    }

    private int dfs(int id) {

        Employee emp = map.get(id);

        int total = emp.importance;

        for (int subId : emp.subordinates) {
            total += dfs(subId);
        }

        return total;
    }
}

