import React from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios'; // don't forget this

const test = <div>Hello!</div>; // jsx can be a variable

const App = () => {
  // pass in default value into useState
  const [note, setNote] = React.useState(''); // create a state variable + setter
  const [notes, setNotes] = React.useState([]); // if map of undefined 

  const fetchNotes = () => {
    // utility to get all notes
    axios.get('/api/getAllNotes')
      .then((res) => {
        console.log(res);
        setNotes(res.data.notes); // update state variable
      })
      .catch(console.log);
  };

  const submitNote = () => { // arrow/lambda function
    console.log(note);
    const body = {
      note: note
    };
    axios.post('/api/addNote', body)
      .then(() => setNote(''))
      .then(() => fetchNotes()) // fetch after submit
      .catch(console.log);
  };

  // this is a hook
  React.useEffect(() => {
    // this will load notes when the page loads
    fetchNotes();
  }, []); // pass empty array

  // jsx
  return (
    <div className="App">
      <header className="App-header">
        <div>
          <div>
            <input value={note} onChange={e => setNote(e.target.value)} />
          </div>
          <div>
            <button onClick={submitNote}>Add Note</button>
          </div>
          <div>
            {notes.map((item) => {
              // same as java
              // convert each array item to an element
              return (
                <div>
                  {item}
                </div>
              );
            })}
          </div>
        </div>
      </header>
    </div>
  );
};

export default App;
