import {initializeApp} from 'firebase/app';
import {getDatabase, onValue, ref} from 'firebase/database';

export const App = () => (
  <>
    I'm Heimdall!
    <div/>
  </>
);

export default App;

const firebaseConfig = {
  apiKey: "${{ secrets.apiKey }}",
  authDomain: "${{ secrets.authDomain }}",
  projectId: "${{ secrets.projectId }}",
  storageBucket: "${{ secrets.storageBucket }}",
  messagingSenderId:  "${{ secrets.messagingSenderId }}",
  appId: "${{ secrets.appId }}",
  measurementId: "${{ secrets.measurementId }}",
  databaseURL: "${{ secrets.databaseURL }}",
};

const app = initializeApp(firebaseConfig);
const db = getDatabase(app);
const starCountRef = ref(db, 'projects/');
onValue(starCountRef, (snapshot) => {
  const data = snapshot.val();
});
