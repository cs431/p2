// File: CPU.h
// Discription: This is the CPU simulator that will grab a file from 
//				an consol input and return virtual memory address from the file.


#pragma once
#include<fstream>
#include<string>
#include<queue>

#include "VirtualMemoryAddr.h"

using namespace std;

class CPU
{
public:
	// define static functions here
	static void initCPU(string);

	static VirtualMemoryAddr readVirtualMemoryAddr();

private:
	static ifstream infile;
};

