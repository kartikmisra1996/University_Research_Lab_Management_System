<?php
$my_dir = dirname(__FILE__); 
require_once $my_dir . '/../model/LaboratoryManager.php';
require_once $my_dir . '/../model/Employee.php';
require_once $my_dir . '/../persistence/Persistence.php';
require_once 'PHPUnit/Autoload.php';

class PersistenceTest extends PHPUnit_Framework_TestCase {
    
    protected $pm;
    
    protected function setUp()
    {
        $this->pm = new Persistence();
    }
    
    protected function tearDown()
    {
    }
    
    public function testPersistence()
    {
        // 1. Create test data
        $lab = LaboratoryManager::getInstance();
        
        $employee = new Employee("Frank Comeau", "director", "working on implementation", 100);
        $lab->addEmployee($employee);
        
        // 2. Write all of the data
        $this->pm->writeDataToStore($lab);
        
        // 3. Clear the data from memory
        $lab->delete();
        
        $this->assertEquals(0, count($lab->getEmployees()));
        
        // 4. Load it back in
        $lab = $this->pm->loadDataFromStore();
        
        // 5. Check that we got it back
        $this->assertEquals(1, count($lab->getEmployees()));
        $myEmployee = $lab->getEmployee_index(0);
        $this->assertEquals("Frank Comeau", $myEmployee->getName());
       
        
    }
    
    
    
}

