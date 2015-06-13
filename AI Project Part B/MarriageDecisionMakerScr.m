%Clearing the variables
clear

%Storing the user inputs
Pc = input('Rate personal character between 1 and 10 > ');
Ed = input('Rate education between 1 and 10 > ');
Fs = input('Rate financial standing between 1 and 10 > ');
B = input('Rate beauty between 1 and 10 > ');
Fl = input('Rate history knowledge between 1 and 10 > ');

%Converting input to appropriate format
Pc = Pc/10;
Ed = Ed/10;
Fs = Fs/10;
B = B/10;
Fl = Fl/10;

%Creating an input set for the inference
input = [Pc; Ed; Fs; B; Fl];

%Reading fis file creating using fuzzy toolbox
fisF = readfis('MarriageDecisionMaker.fis');

display('Your compatibility is ');

%Calculating and displaying output
display(evalfis(input, fisF)*100);


%Opening fuzzy inference system
ruleview(fisF);

fuzzy(fisF);

ruleedit(fisF);

mfedit(fisF);

surfview(fisF);


