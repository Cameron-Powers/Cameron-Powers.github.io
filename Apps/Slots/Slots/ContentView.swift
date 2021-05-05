//
//  ContentView.swift
//  Slots
//
//  Created by Cameron Powers on 2020-03-30.
//  Copyright © 2020 Cameron Powers. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    
    private var symbols = ["apple", "star", "cherry"]
    @State private var numbers = [1,0,2]
    @State private var credits = 1000
    @State private var activeAlert: ActiveAlert = .first
    @State var betAmount = 50
    @State var showAlert = false
    @State var showAlertLose = false
    func increaseBet(){
        betAmount+=1
    }
    func decreaseBet(){
        betAmount-=1
    }
    var body: some View {
        ZStack{
            // Background
            Rectangle()
                .foregroundColor(Color(red: 200/255, green: 143/255, blue: 32/255))
                .edgesIgnoringSafeArea(.all)
            Rectangle()
                .foregroundColor(Color(red: 228/255, green: 195/255, blue: 76/255))
                .rotationEffect(Angle(degrees: 45))
                .edgesIgnoringSafeArea(.all)
            VStack{
                Spacer()
                // Title
                HStack{
                    Image(systemName: "star.fill")
                        .foregroundColor(.yellow)
                    Text("Casino Slots")
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    Image(systemName: "star.fill")
                        .foregroundColor(.yellow)
                }.scaleEffect(2)
                Spacer()
                // Credits Counter
                Text("Credits: " + String(credits))
                    .foregroundColor(.black)
                    .padding(.all, 10)
                    .background(Color.white.opacity(0.5))
                    .cornerRadius(20)
                Spacer()
                    .frame(height: 10)
                Text("Bet Amount: " + String(self.betAmount))
                    .foregroundColor(.black)
                    .padding(.all, 10)
                    .background(Color.white.opacity(0.5))
                    .cornerRadius(20)
                
                Spacer()
                // Cards
                HStack{
                    Spacer()
                    Image(symbols[numbers[0]])
                        .resizable()
                        .aspectRatio(1, contentMode: .fit)
                        .background(Color.white.opacity(0.5))
                        .cornerRadius(20)
                    Image(symbols[numbers[1]])
                        .resizable()
                        .aspectRatio(1, contentMode: .fit)
                        .background(Color.white.opacity(0.5))
                        .cornerRadius(20)
                    Image(symbols[numbers[2]])
                        .resizable()
                        .aspectRatio(1, contentMode: .fit)
                        .background(Color.white.opacity(0.5))
                        .cornerRadius(20)
                    Spacer()
                }
                Spacer()
                // Button
                VStack{
                    Button(action: {
                        // Change images
                        self.numbers[0] = Int.random(in:0...self.symbols.count-1)
                        self.numbers[1] = Int.random(in:0...self.symbols.count-1)
                        self.numbers[2] = Int.random(in:0...self.symbols.count-1)
                        
                        // Check for win
                        if (self.numbers[0] == self.numbers[1]) && (self.numbers[1] == self.numbers[2]) {
                            // Winner
                            self.activeAlert = .first
                            self.showAlert.toggle()
                            self.credits += self.betAmount * 2
                        } else {
                            self.credits -= self.betAmount
                        }
                        // Loser
                        if(self.credits <= 0){
                            self.activeAlert = .second
                            self.showAlert.toggle()
                            self.credits = 1000
                        }
                    }) {
                        Text("Spin")
                            .bold()
                            .foregroundColor(.white)
                            .padding(.all,10)
                            .padding([.leading,.trailing], 30)
                            .background(Color.pink)
                            .cornerRadius(20)
                    }
                    Spacer()
                        .frame(height: 10)
                    Button(action: {
                        self.increaseBet()
                    }) {
                        Text("Tap to Increase Bet")
                            .bold()
                            .foregroundColor(.white)
                            .padding(.all,10)
                            .padding([.leading,.trailing], 30)
                            .background(Color.pink)
                            .cornerRadius(20)
                    }
                    Spacer()
                        .frame(height: 10)
                    Button(action: {
                        self.decreaseBet()
                    }) {
                        Text("Tap to Decrease Bet")
                            .bold()
                            .foregroundColor(.white)
                            .padding(.all,10)
                            .padding([.leading,.trailing], 30)
                            .background(Color.pink)
                            .cornerRadius(20)
                    }
                }
            }
        }
        .alert(isPresented: $showAlert) {
            switch activeAlert {
            case .first:
                return Alert(title: Text("Winner"), message: Text("You won " + String(self.betAmount * 2) + " Credits!"), dismissButton: .default(Text("Yay!")))
            case .second:
                return Alert(title: Text("Game Over"), message: Text("You Ran out of credits"), dismissButton: .default(Text("Play Again")))
            }
        }
    }
}

enum ActiveAlert {
    case first, second
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
