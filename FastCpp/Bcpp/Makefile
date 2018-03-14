CC		= g++
CFLAGS	= -g3 -Wall
EXEC 	= Bcpp
OBJS	= b.o
SRCS	= b.cpp

.PHONY : all
all: $(EXEC)

$(EXEC): $(OBJS)
	$(CC) $(CFLAGS) -o $@ $(OBJS)

b.o: b.cpp
	$(CC) $(CFLAGS) -c b.cpp


.PHONY: clean
clean:
	rm -f $(OBJS) $(EXEC)


.PHONY: run
run:
	./$(EXEC)