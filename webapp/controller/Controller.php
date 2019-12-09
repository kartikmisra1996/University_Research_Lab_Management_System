<?php
$my_dir = dirname(__FILE__);
require_once $my_dir . '/../persistence/Persistence.php';
require_once $my_dir . '/../model/Employee.php';
require_once $my_dir . '/../model/LaboratoryManager.php';
require_once $my_dir . '/../model/ProgressUpdate.php';
require_once $my_dir . '/../model/Item.php';
require_once $my_dir . '/../model/Supply.php';
require_once $my_dir . '/../model/Transaction.php';
require_once $my_dir . '/../model/Equipment.php';
require_once $my_dir . '/../model/FundingAccount.php';
require_once $my_dir . '/../model/Paystub.php';
require_once $my_dir . '/../EmployeeIdCounter.php';
require_once $my_dir . '/../controller/FundingChecker.php';

class Controller
{
    public function __construct()
    {
    }
        
    public function createEmployee($employee_name, $employee_job, $employee_role, $employee_salary) {
        // 1. Validate input
        $name = $employee_name;
        $job = $employee_job;
        $role = $employee_role;
        $salary = $employee_salary;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Employee name cannot be empty!");
        
        } else if ($job == null || strlen($job) == 0) {
            throw new Exception("Employee job cannot be empty!");
            
        } else if ($role == null || strlen($role) == 0) {
            throw new Exception("Employee role cannot be empty!");
            
        } else if ($salary == null) {
            throw new Exception("Employee salary cannot be empty!");
                       
        } else if ($salary < 0) {
            throw new Exception("Employee salary cannot be negative!");
            
        } else {
            // 2. Load all of the data
            //Verify existence of funding account
            $checker = new FundingChecker();
            $checker->initiateFunding();
            
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            //Increase employee ID counter to always get new unique ID numbers
            $idCounter = new EmployeeIdCounter();
            $id = $idCounter->loadCounter();
            $idCounter->increaseCounter();
            
            // 3. Add the new Employee
            $employee = new Employee($name, $job, $salary, $role, $id);
            $lab->addEmployee($employee);
                
            //Create paystub transaction of amount 0$ to indicate the astart of pay period
            $date = date('Y/m/d', time());
            $description = "Start of pay period for new employee \"" . $name . "\", will receive first pay next Thursday";
            $amount = 0;
            $paystub = new Paystub($date, $description, $amount, $id);
                
            $fundingAccount = $lab->getFundingAccount_index(0);
            $fundingAccount->addTransaction($paystub);
            
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
        }
        
    }
    
    public function deleteEmployee($employee_name) {
        // 1. Validate input
        $name = $employee_name;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Employee name cannot be empty!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            $found = false;
            $index = 0;
            
            //Find index of employee with matching name
            for ($i = 0; $i < $lab->numberOfEmployees(); $i++) {
                $myEmployee = $lab->getEmployee_index($i);
                if ($myEmployee->getName() == $name){
                    $found = true;
                    $index = $i;
                    break;
                }
                
            }
            
            if ($found != 1) {
                throw new Exception("Employee not found");
            } else {
                
                // 3. Remove the employee
                $myEmployee = $lab->getEmployee_index($index);
                $lab->removeEmployee($myEmployee);
                
               // 4. Write all of the data
                $pm->writeDataToStore($lab);
            }
        }
    }
    
    public function modifyEmployee($employee_index, $employee_name, $employee_job, $employee_role, $employee_salary) {
        // 1. Validate input
        $name = $employee_name;
        $job = $employee_job;
        $role = $employee_role;
        $salary = $employee_salary;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Employee name cannot be empty!");
            
        } else if ($job == null || strlen($job) == 0) {
            throw new Exception("Employee job cannot be empty!");
            
        } else if ($role == null || strlen($role) == 0) {
            throw new Exception("Employee role cannot be empty!");
            
        } else if ($salary == null) {
            throw new Exception("Employee salary cannot be empty!");
            
        } else if ($salary < 0) {
            throw new Exception("Employee salary cannot be negative!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            // 3. Set employee attributes
            $myEmployee = $lab->getEmployee_index($employee_index);
            $myEmployee->setName($employee_name);
            $myEmployee->setJob($employee_job);
            $myEmployee->setRole($employee_role);
            $myEmployee->setSalary($employee_salary);            
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
        }
        
    }
    
    public function createProgress($employee_index, $progress_day, $progress_month, $progress_year, $progress_objective, $progress_actualwork) {
        // 1. Validate input
        $index = $employee_index;
        $day = $progress_day;
        $month = $progress_month;
        $year = $progress_year;
        $objective = $progress_objective;
        $actualWork = $progress_actualwork;
        
        $date = $progress_year . "/" . $progress_month . "/" . $progress_day;
        
        if ($day == null || strlen($day) == 0 || $day > 31 || $day < 1 ||
            $month == null || strlen($month) == 0 || $month > 12 || $month < 1 ||
            $year == null || strlen($year) != 4) {
            throw new Exception("Invalid date");
            
        } else if ($objective == null || strlen($objective) == 0) {
            throw new Exception("Objective cannot be empty!");
            
        } else if ($actualWork == null || strlen($actualWork) == 0) {
            throw new Exception("Work done cannot be empty!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            // 3. Add the new progress update
            $myProgressUpdate = new ProgressUpdate($objective, $actualWork, $date);
            $myEmployee = $lab->getEmployee_index($employee_index);
            $myEmployee->addProgressUpdate($myProgressUpdate);
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
        }
    }
    
    public function deleteProgress($employee_index, $progress_index) {
        
        $employeeIndex = $employee_index;
        $progressIndex = $progress_index;
        
            // 1. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();     
            
                // 2. Remove the progress update
                $myEmployee = $lab->getEmployee_index($employeeIndex);
                $myProgressUpdate = $myEmployee->getProgressUpdate_index($progressIndex);
                $myEmployee->removeProgressUpdate($myProgressUpdate);
                
                // 3. Write all of the data
                $pm->writeDataToStore($lab);
    }
    
    public function createSupply($supply_name, $supply_price, $supply_quantity, $supply_available) {
        // 1. Validate input
        $name = $supply_name;
        $price = $supply_price;
        $quantity = $supply_quantity;
        $available = $supply_available;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Item name cannot be empty!");
            
        } else if ($price == null || strlen($price) == 0) {
            throw new Exception("Item base price cannot be empty!");
            
        } else if ($quantity == null || strlen($quantity) == 0) {
            throw new Exception("Item quantity cannot be empty!");
            
        } else if ($price < 0) {
            throw new Exception("Item base price cannot be negative!");
            
        } else if ($quantity < 0) {
            throw new Exception("Item quantity cannot be negative!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            // 3. Add the new Supply
            $supply = new Supply($name, $price, $quantity, $available);
            $lab->addItem($supply);
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
        }
    }
    
    public function modifyItem($item_index, $item_name, $item_price, $item_prev_quantity, $item_new_quantity) {
        // 1. Validate input
        $name = $item_name;
        $price = $item_price;
        $prevquantity = $item_prev_quantity;
        $newquantity = $item_new_quantity;
  
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Item name cannot be empty!");
            
        } else if ($price == null || strlen($price) == 0) {
            throw new Exception("Item base price cannot be empty!");
            
        } else if ($newquantity == null || strlen($newquantity) == 0) {
            throw new Exception("Item quantity cannot be empty!");
            
        } else if ($price < 0) {
            throw new Exception("Item base price cannot be negative!");
            
        } else if ($newquantity < 0) {
            throw new Exception("Item quantity cannot be negative!");
            
        } else {
            // 2. Load all of the data
            $checker = new FundingChecker();
            $checker->initiateFunding();
            
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            // 3. Set employee attributes
            $myItem = $lab->getItem_index($item_index);
            $itemtype = get_class($myItem);
            if ($itemtype == "Supply") {
                $available = true;
            }
            
            $myItem->setName($item_name);
            $myItem->setPrice($item_price);
            $myItem->setQuantity($item_new_quantity);
            if ($itemtype == "Supply") {
                if ($item_new_quantity > 0){
                    $myItem->setIsAvailable(true);
                } else {
                    $myItem->setIsAvailable(false);
                }
            }
            
            // 4. Create transaction if quantity was increased
            if ($item_prev_quantity < $item_new_quantity) {
                
                                
                $date = date('Y/m/d', time());
                $description = "Item \"" . $item_name . "\" was purchased " . ($item_new_quantity - $item_prev_quantity) . " times, with a unit cost of " . $item_price." $";
                $amount = ($item_new_quantity - $item_prev_quantity)*$item_price*(-1);
                $transaction = new Transaction($date, $description, $amount);
                
                $fundingAccount = $lab->getFundingAccount_index(0);
                $fundingAccount->addTransaction($transaction);
                $fundingAccount->setBalance($fundingAccount->getBalance()+$amount);
            }
            
            // 5. Write all of the data
            $pm->writeDataToStore($lab);
        }
        
    }
    
    public function useSupply($item_index) {
        // 1. Load data
        
        $pm = new Persistence();
        $lab = $pm->loadDataFromStore();
        $myItem = $lab->getItem_index($item_index);
        
        // 2. Validate input

        $prevquantity = $myItem->getQuantity();        
        
        if ($prevquantity < 1) {
            throw new Exception("Item is already out of stock!");
            
        } else {
            
            // 3. Set item attributes
            $newquantity = $prevquantity - 1;
            $myItem->setQuantity($newquantity);
            
            if ($newquantity > 0){
                 $myItem->setIsAvailable(true);
             } else {
                 $myItem->setIsAvailable(false);
             }
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
        }
        
    }
    
    public function deleteItem($item_name) {
        // 1. Validate input
        $name = $item_name;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Item name cannot be empty!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            $found = false;
            $index = 0;
            
            for ($i = 0; $i < $lab->numberOfItems(); $i++) {
                $myItem = $lab->getItem_index($i);
                if ($myItem->getName() == $name){
                    $found = true;
                    $index = $i;
                    break;
                }
                
            }
            
            if ($found != 1) {
                throw new Exception("Item not found");
            } else {
                
                // 3. Remove the item
                $myItem = $lab->getItem_index($index);
                $lab->removeItem($myItem);
                
                // 4. Write all of the data
                $pm->writeDataToStore($lab);
            }
        }
    }
    
    public function createEquipment($equipment_name, $equipment_price, $equipment_quantity) {
        // 1. Validate input
        $name = $equipment_name;
        $price = $equipment_price;
        $quantity = $equipment_quantity;
        if ($name == null || strlen($name) == 0) {
            throw new Exception("Item name cannot be empty!");
            
        } else if ($price == null || strlen($price) == 0) {
            throw new Exception("Item base price cannot be empty!");
            
        } else if ($quantity == null || strlen($quantity) == 0) {
            throw new Exception("Item quantity cannot be empty!");
            
        } else if ($price < 0) {
            throw new Exception("Item base price cannot be negative!");
            
        } else if ($quantity < 0) {
            throw new Exception("Item quantity cannot be negative!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            // 3. Add the new Employee
            $equipment = new Equipment($name, $price, $quantity);
            $lab->addItem($equipment);
            
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
            
            
        }
    }
    
    public function addFunds($funds_amount) {
        // 1. Validate input
        $amount = $funds_amount;
        if ($amount == null || strlen($amount) == 0) {
            throw new Exception("Amount cannot be empty!");
        
        } else if ($amount < 0) {    
            throw new Exception("Amount cannot be negative!");
            
        } else {
            // 2. Load all of the data
            //Verify existence of funding account
            $checker = new FundingChecker();
            $checker->initiateFunding();
            
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();

            $myFunding = $lab->getFundingAccount_index(0);
                              
            $myFunding->setBalance($myFunding->getBalance() + $funds_amount);
            
            $date = date('Y/m/d', time());
            $description = "Added funds";
            $myTransaction = new Transaction($date, $description, $funds_amount);
            $myFunding->addTransaction($myTransaction);
                
                // 4. Write all of the data
                $pm->writeDataToStore($lab);
            
        }
    }
    
    public function createTransaction($transaction_description, $transaction_amount) {
        // 1. Validate input
        $amount = $transaction_amount;
        $date = date('Y/m/d', time());
        
        $description = $transaction_description;
        if ($amount == null || strlen($amount) == 0) {
            throw new Exception("Amount cannot be empty!");
            
        } else if ($amount < 0) {
            throw new Exception("Amount cannot be negative!");
            
        } else if ($description == null || strlen($amount) == 0) {
            throw new Exception("Description cannot be empty!");
            
        } else {
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            //Verify existence of funding account
            
            $checker = new FundingChecker();
            $checker->initiateFunding();
            
            $transaction = new Transaction($date, $description, (-1)*$amount);
            $fundingAccount = $lab->getFundingAccount_index(0);
            $fundingAccount->addTransaction($transaction);
            $fundingAccount->setBalance($fundingAccount->getBalance()-$amount);
            // 4. Write all of the data
            $pm->writeDataToStore($lab);
            
        }
    }
    
    public function updatePayroll() {
        
        //Verify existence of funding account
        $checker = new FundingChecker();
        $checker->initiateFunding();
            
            // 2. Load all of the data
            $pm = new Persistence();
            $lab = $pm->loadDataFromStore();
            
            $fundingAccount = $lab->getFundingAccount_index(0);
            
            //for all employees
            for ($i = 0; $i < $lab->numberOfEmployees(); $i++) {
                $myEmployee = $lab->getEmployee_index($i);
                $id = $myEmployee->getId();
    
                $lastPay = new Paystub("1000/01/01", "a", 0, 1);
                
                //find latest Paystub
                for ($j = 0; $j < $fundingAccount->numberOfTransactions(); $j++) {
                    $myTransaction = $fundingAccount->getTransaction_index($j);
                        
                    if (get_class($myTransaction) == "Paystub") {
                        if ($myTransaction->getEmployeeID() == $id) {
                            if ($myTransaction->getDate() > $lastPay->getDate()) {
                                
                                $lastPay = $myTransaction; 
                            }
                        }
                    }
                }
                
                //Pay employee for every thursday since last pay
                $lastThursday = date('Y/m/d',strtotime('-1 Thursday'));
                for ($d = $lastThursday, $x = -1; $d > $lastPay->getDate(); $x-- , $d = date('Y/m/d',strtotime($x.' Thursday'))){
                    
                    $description = "Employee \"" . $myEmployee->getName() . "\" was paid ".$myEmployee->getSalary()." $";
                    $paystub = new Paystub($d, $description, (-1)*($myEmployee->getSalary()), $myEmployee->getId());
                    $fundingAccount->addTransaction($paystub);
                    // 4. Write all of the data
                    $fundingAccount->setBalance($fundingAccount->getBalance()+$paystub->getAmount());
                    $pm->writeDataToStore($lab);  
                }
            }
    }
}

?>