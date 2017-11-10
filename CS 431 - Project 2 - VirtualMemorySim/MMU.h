// File: MMU.h
// Discription: This class will mimic the MMU and take a vurtual memory address obj and process it accordingly.

#pragma once
#include "VirtualMemoryAddr.h"

using namespace std;

class MMU
{
public:
	MMU();
	// define static functions here
	void processVMAddr(VirtualMemoryAddr*);
};

