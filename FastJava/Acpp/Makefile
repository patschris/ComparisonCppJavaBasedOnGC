CC		= g++
CFLAGS	= -g3 -Wall
EXEC 	= Acpp
OBJS	= a.o
SRCS	= a.cpp

.PHONY : all
all: $(EXEC)

$(EXEC): $(OBJS)
	$(CC) $(CFLAGS) -o $@ $(OBJS)

a.o: a.cpp
	$(CC) $(CFLAGS) -c a.cpp


.PHONY: clean
clean:
	rm -f $(OBJS) $(EXEC)

.PHONY: run
run:
	./$(EXEC)