#include "CPU.h"

CPU::CPU()
{
	
}

VirtualMemoryAddr* CPU::readVirtualMemoryAddr(string fileAddr)
{
	if (!infile.is_open()) 
	{
		infile.open(fileAddr);
	}
	char writeBit;
	string vma;
	double dec;
	if (!infile.eof()) 
	{
		infile >> writeBit >> vma;
		if (writeBit == '1') 
		{
			infile >> dec;
			return new VirtualMemoryAddr(writeBit, vma, dec);
		}
		return new VirtualMemoryAddr(writeBit, vma);
	}
	else 
	{
		infile.close();
		return NULL;
	}
	
}
