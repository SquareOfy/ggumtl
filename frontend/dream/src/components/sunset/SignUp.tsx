// 로고 이미지
// 닉네임인풋
// 프로필사진 업로드 버튼
// 프로필사진 전용 이미지 태그
// 다음버튼 -> <SunsetMainPage/>

import React, {useEffect, useState, useRef} from "react";
import styled from "styled-components";
import Input from "style/Input";
import Image from "style/Image";
import Button from "components/common/Button";
import Text from "style/Text";
import { FaCircleCheck,FaCircleXmark } from "react-icons/fa6";
import { useNavigate } from "react-router-dom";
import basicHttp from "api/basicHttp";
// import { BoxTitle } from "style/Box";


// 회원가입 박스
const SignUpContainer = styled.div`
  padding: 2rem;
  /* display: flex; */
  flex-direction: column;
  align-items: center;
  justify-content: center;
`

const CheckMessageBox = styled.div`
  display: flex;
  align-items: center;
  padding-left: 0.5rem;
  padding-bottom: 1rem;
`


const SignUp = () => {

  const navigate = useNavigate()

  const [profileImage, setProfileImage] = useState<File | null>(null)
  const [profileImageURL, setProfileImageURL] = useState<string | undefined>(undefined)
  const [nicknameInput,setNicknameInput] = useState('') 

  // good 한글 7자 이하, 옳은 닉네임 / bad 자음만 있는 닉네임 / double 중복된 닉네임
  const [wrongNicknameSign, setWrongNicknameSign] = useState('good')

  // useRef를 이용해서 input 태그에 접근
  const imageInput = useRef<HTMLInputElement | null>(null);
  const imageInputClick = () => {
    imageInput.current?.click()
  }
  // 이미지 미리보기 
  const imageUpload = (e:React.ChangeEvent<HTMLInputElement>):void => {
    e.preventDefault();

    if (!e.target.files) {return}
    const file = e.target.files[0]

    if (file) {
      let image = window.URL.createObjectURL(file)
      setProfileImageURL(image)
      setProfileImage(file);
      // console.log(image,'image')
      // console.log(file,'file')
    }
  }

  const checkNickname = (nickname:string):void => {
    const regex = /^[ㄱ-ㅎ가-힣]*$/;
    // 한글이고 7자까지 가능
    if (regex.test(nickname) && (nickname.length <= 7) ){
      setNicknameInput(nickname)
      setWrongNicknameSign('good')
    }
  }
  
  // 닉네임 중복 체크 함수
  const checkDoubleNickname = (nickname:string):void => {
    console.log('닉네임 중복 체크 api보냄')
    // axios(url) 보냈는데 
    // response 가 중복(true)이라면 -> setWrongNicknameSign('double')
    // 중복 x -> setWrongNicknameSign('good')
    basicHttp.get(`/user/nickname/duplication/${nickname}`)
      .then((response)=>{
        console.log(response)
      })
      .catch((e) => console.log(e))
  }

  // 가입 완료 버튼 클릭 시 함수
  const signupComplete = () => {
    const consRegex = /^[ㄱ-ㅎ]*$/;
    if (consRegex.test(nicknameInput)){
      // 닉네임 잘못 입력 체크
      setWrongNicknameSign('bad')
      // 경고창 예쁜걸로 수정
      alert('닉네임을 올바르게 입력해주세요.')
    }
    else {
      // 가입이 완료되었습니다 페이지 있으면 좋겠음..
      navigate('/sunset/main')
    }
  }

  return (
    <SignUpContainer>
      {/* 로고 */}
      {/* 닉네임 인풋 */}
      <Input 
        $nicknameInputBar 
        type="text" 
        placeholder="닉네임" 
        value={nicknameInput}
        onChange={(e)=>{checkNickname(e.target.value)}}
        onBlur={(e)=>{checkDoubleNickname(e.target.value)}}
      ></Input>
      {/* 경고창 */}
      <CheckMessageBox>
        { wrongNicknameSign === 'good' && 
          <>
            <FaCircleCheck color= "#198754"/>
            <Text $successMessage>한글 7자, 영어/숫자는 입력이 안됩니다.</Text>
          </>
        }
        { wrongNicknameSign === 'bad' && 
          <>
            <FaCircleXmark color= "#dc3545"/>
            <Text $wrongMessage>자음만 입력할 수 없습니다.</Text>
          </>
        }
        { wrongNicknameSign === 'double' && 
          <>
            <FaCircleXmark color= "#dc3545"/>
            <Text $wrongMessage>닉네임 중복입니다.</Text>
          </>
        }
      </CheckMessageBox>

      <Button 
        $fullWidth 
        $nightPalePurple 
        style={{color:'black'}}
        onClick={imageInputClick}
      >프로필 사진 업로드</Button>

      {/* 프로필 이미지 미리보기*/}
      <Image $signupImage>
        <img src={ profileImageURL ? profileImageURL : `${process.env.PUBLIC_URL}/image/default-profile.png`}></img>
      </Image>
      <input 
        type="file"
        accept="image/*"
        style={{display:'none'}} // 화면에서 안보이게, 하지만 ref를 통해 기능은 가져감
        ref = {imageInput}
        onChange={imageUpload}
      ></input>
      
      {/* 다음 */}
      <Button 
        $fullWidth 
        $sunsetPink
        onClick={signupComplete}
      >가입 완료</Button>

    </SignUpContainer>
  )
}
export default SignUp 