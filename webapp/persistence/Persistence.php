<?php

$my_dir = dirname(__FILE__);
require_once $my_dir . '/../model/LaboratoryManager.php';

class Persistence   {
    private $filename;
    
    function __construct($filename = 'data.txt') {
        $this->filename = $filename;
    }
    
    function loadDataFromStore() {
        if (file_exists($this->filename)) {
            //If file exists, load file
            $str = file_get_contents($this->filename);
            $lab = unserialize($str);
        } else {
            //Else create file
           // $lab = LaboratoryManager::getInstance();
           $lab = new LaboratoryManager;
        }
        
        return $lab;
    }
    
    function writeDataToStore($lab) {
        $str = serialize($lab);
        file_put_contents($this->filename, $str);
    }
}
