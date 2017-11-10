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
	CPU();

	VirtualMemoryAddr* readVirtualMemoryAddr(string);

private:
	ifstream infile;
};

