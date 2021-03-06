// File: VirtualMemoryAddr.h
// Discription: This is a virtual memory address as depicted in the text files provided from the zip file.
//				Consiting of a Write to memory bit and a four byte address value.

#pragma once
#include <string>

using namespace std;

class VirtualMemoryAddr
{
public:
	VirtualMemoryAddr(char,string);
	bool getWTM();
	void setWTM(char);
	string getVMAddr();
	void setVMAddr(string);
	~VirtualMemoryAddr();
private:
	bool writeToMem;
	string vmAddr;
};

