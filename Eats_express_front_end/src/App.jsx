import './App.css'
import { Outlet} from 'react-router-dom'
import NavbarComp from './Components/NavbarComp'
import FooterComp from './Components/FooterComp'
function App() {
 
  return (
    <>
    <NavbarComp />
    <div className='min-h-screen'>
    <Outlet/>
      
    </div>
    <FooterComp />
    </>
  )
}

export default App
