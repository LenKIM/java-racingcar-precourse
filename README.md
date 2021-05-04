# 자동차 경주 게임
## 진행 방법
* 자동차 경주 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)



## 기능 요구사항

- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출수있다.

- 각 자동차에 이름을 부여 할 수 있다. 전진하는 자동차를 출력 할 때 자동차 이름을 같이 출력한다.

- 자동차 이름은 쉼표(,)를 기준으로 구분하며 이름은 5자이하만 가능하다.

- 사용자는 몇번의 이동을 할 것인지를 입력 할 수 있어야 한다.

- 전진하는 조건은 0에서9사이에서 random값을 구한 후 random값이 4이상일 경우 전진하고,3이하의 값이면 멈춘다.

- 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 
- 우승자는 한명 이상 일 수있다.



## 기능 목록 리스트

1. - [x] 각 자동차는 전진 또는 멈춤을 할 수 있다.
     - [x] 각 자동차는 5자이하의 이름을 가진다. 그렇지 않을 경우, IllegalArgumentException 을 발생시킨다.
     - [x] 처음 위치는 시작점이여야 한다.
     - [x] 자동차는 전진 또는 멈춤 할 수 있는 조건을 가진 엑셀레이터(accelerator)를 가진다.
     - [x] 엑셀레이터(accelerator)는 특정 조건을 가져, 자동차를 전진 또는 멈춤할 수 있다.
       - [x] 엑셀레이터의 조건이 True일 경우, 자동차는 전진한다.
       - [x] 엑셀레이터의 조건이 False일 경우, 자동차는 멈춤한다.
   
2. - [x] 프로그램(RacingGame)은 사용자로부터 ',' 로 구성된 문자열을 입력받아, 이름을 가진 복수의 자동차를 생산한다.
     - [x] 사용자로부터 ',' 문자가 포함된 문자열을 입력받는다. 이때 ',' 가 없다면 자동차 1대이다. 그러나 레이싱은 혼자서 할 수 없으므로, 2대 이상을 받아야 할 것이다.
     - [x] 문자열을 ',' 로 나눈 횟수 만큼의 복수의 자동차를 생산한다.
     - [x] 이 때 생산된 자동차는 모두 출발점에 위치해야 한다.
     - [x] 일급 컬렉션을 사용하여, 자동차가 가져야할 불변성을 지킬수 있도록 한다.
       - [x] 처음 생성시 시작지점은 모두 0이다.
     - [x] 사용자 입력이 유효한지 테스트한다.
       - [x] ',' 가 포함된 값을 입력되지 않을 지 예외를 호출한다.
       - [x] 값을 입력하지 않을 경우 또는 null을 입력하게 되면 예외를 호출한다
     - [x] 자동차 이름이 중복을 가질 경우 {name}A, {name}B, {name}C 로 표기한다
     - [x] 자동차 이름에 공백을 가질 경우, 공백은 제거된다.
   
3. - [x] 요구사항에 맞는 엑셀레이터(accelerator)를 제작한다.
     - [x] 엑셀레이터(accelerator)는 엔진(Engine)에 의해 동작된다.
     - [x] 엔진은 0-9 사이에 랜덤으로 출력되는 값에 따라 엑셀레이터를 전진 또는 멈춤한다.
       - [x] 4 이상의 값이 출력시 엑셀레이터의 엔진은 동작한다
       - [x] 3 이하의 값이 출력시 엑셀레이터의 엔진은 멈춥다.

4. - [ ] 입력받은 시도 횟수만큼 따라 각각의 자동차는 엑셀레이터(accelerator)에 의해서 전진 또는 멈춤을 실행한다.
     - [x] String 타입의 carName을 객체로 변경한다.
     - [x] 위치정보(currentLocation) int 자료형을 객체로 변경한다.
     - [ ] 프로그램(RacingGame)은 사용자로부터 시도 횟수를 입력 받을 수 있다
     - [ ] 프로그램(RacingGame)는 횟수를 받는 즉시, 레이싱 경기를 시작한다.
     - [ ] 1회 시도 횟수가 끝날 때마다, 복수의 자동차는 엑셀레이터에 의해 전진 또는 정지가 결정된다.
       - [ ] 레이스가 진행되는 동안 각 레이싱카의 기록을 저장한다.

5. - [ ] 입력받은 시도 횟수 만큼 자동차의 전진이 끝났다면, 가장 많이 전진된 자동차가 우승자 이다.
     - [ ] 프로그램(RacingGame)은 일급 컬렉션으로 만든 cars에서 가장 많이 전진된 자동차를 선별한다.

       - [ ] 가장 많이 전진된 횟수를 가진 자동차 찾는다.
     - [ ] 우승카는 1대 이상이 될 수 있다.
     
       - [ ] 모든 카가 전진되지 않았다면 모든 카가 공동 우승이다.
       - [ ] 모든 카가 함께 모두 전진되었다면 모든 카가 공동 우승이다.

6. - [ ] name 문자열을 carName 객체로 변경한다.
   - [ ] RacingCars를 생성하는 코드에 일관된 생성방법을 가질 수 있도록 만들기
   - [ ] 



 

## 메모

- 바로 출력되는건 게임으로서 즐거움을 주기 부족하다. Delay 를 살짝 주는건 어떠할까?

- 랜덤의 숫자를 호출하는 것은 누가 책임져야할까?
- 전진하라고 요청하는 것은 시키는 것과, 역할을 위임하는 것은 아주 많이 다르다.
- 기능 목록을 작성하면서, 시간이 걸렸던 부분은 '닭이 먼저냐? 계란이 먼저냐?' 와 같이 무엇을 먼저 만들어야 하는가에 대한 고민이 있지만, 직접 코드를 작성하려고 하니 그 순간 고민이 풀렸다. 아마도 머릿속으로 직접 코드로 짜보는 상상을 해보면 조금더 알지 알았을까?

