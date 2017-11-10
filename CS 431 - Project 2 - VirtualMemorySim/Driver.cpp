// this will be our main driver for the project
#include "CPU.h"
#include "MMU.h"
#include <iostream>

using namespace std;

int main() {
	string file;					// file name input
	CPU cpu_sim = CPU();			// cpu simulator
	MMU mmu_sim = MMU();			// mmu simulator
	cout << "Virtual Memory Simulator" << endl;
	cout << "================================================" << endl;
	// loop to process file name input
	while(true)
	{
		// prompt
		cout << "(Type 'exit' to end simulator)" << endl;
		cout << "Please enter the file name> ";
		cin >> file;			// file name
		if (file == "exit")		// if exit, exit
		{
			break;
		}
		while (true)			// else attempt to process file
		{
			VirtualMemoryAddr* v(cpu_sim.readVirtualMemoryAddr(file));	// process file one line at a time
			if (v == NULL)												// if end of file break
			{
				break;
			}
			mmu_sim.processVMAddr(v);	// if processed hand to mmu
		}
	} 
}