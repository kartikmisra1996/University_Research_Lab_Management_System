<?php
//This class is used to assign new ID's when creating new employees
//so that the same ID numbers used for previous employees are not repeated
//The counter.txt file holds a number which will be used as the next new employee's ID
//When a new employee is created this number is incremented by 1

class EmployeeIdCounter   {
    private $filename;
    //Specify file name
    function __construct($filename = 'counter.txt') {
        $this->filename = $filename;
    }
    
    function loadCounter() {
        if (file_exists($this->filename)) {
            //If file exists, load file
            $str = file_get_contents($this->filename);
        } else {
            //Else create file
            $str = 0;
            file_put_contents($this->filename, $str);
        }
        return $str;
    }
    //Called when creating new employees to keep track of the previously used ID's
    function increaseCounter() {
        $str = file_get_contents($this->filename);
        $str += 1; 
        file_put_contents($this->filename, $str);
    }
}

