#include "VirtualMemoryAddr.h"

VirtualMemoryAddr::VirtualMemoryAddr(char wtm, string vma)
{
	setWTM(wtm);
	setVMAddr(vma);
}

bool VirtualMemoryAddr::getWTM()
{
	return writeToMem;
}

void VirtualMemoryAddr::setWTM(char wtm	)
{
	if (wtm = '1') 
	{
		writeToMem = true;
	}
	else 
	{
		writeToMem = false;
	}
}

string VirtualMemoryAddr::getVMAddr()
{
	return vmAddr;
}

void VirtualMemoryAddr::setVMAddr(string vma)
{
	vmAddr = vma;
}

VirtualMemoryAddr::~VirtualMemoryAddr()
{
}
