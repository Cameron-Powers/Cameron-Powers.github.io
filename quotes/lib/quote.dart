class Quote {
  String text;
  String author;
  Quote({this.text, this.author});
}

// Curly brackets in constructor allow for either declaration below:
// Quote myQuote = Quote(text: 'Quote text', author: 'Author name');
// Quote myQuote = Quote(author: 'Author name', text: 'Quote text');