#pragma once
class VM_Entry
{
public:
	int v;
	int r;
	int d;
	int pageFrameNum;

	VM_Entry();
	~VM_Entry();
};

