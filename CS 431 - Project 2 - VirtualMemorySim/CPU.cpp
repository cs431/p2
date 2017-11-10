#include "CPU.h"

void CPU::initCPU(string fileAddr)
{
	infile.open(fileAddr);
}

VirtualMemoryAddr CPU::readVirtualMemoryAddr()
{
	char temp[5] = "";
	if (!infile.eof()) 
	{
		infile.read(temp, 5);
	}
	string vma(temp);
	return VirtualMemoryAddr(vma[0], vma.substr(1,vma.size() - 1));
}
